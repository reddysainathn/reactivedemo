package com.reactive.example.reactivedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rx.Observable;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ReactivedemoApplication {

    public static void main(String[] args) {

        Observable<Users> observable = new ReactivedemoApplication().getData(getUsers());
        observable.subscribe(System.out::println,
                throwable -> System.out.println(" Excpetion:- " + throwable),
                () -> System.out.println("Completed")
        );
    }

    Observable<Users> getData(final List<Users> usersList) {
        return Observable.create(
                subscriber -> {
                    if (!subscriber.isUnsubscribed()) {
                        usersList.stream().forEach(
                                users -> {
                                    subscriber.onNext(users);
                                    sleep(10000);
                                }
                        );
                    }
                    subscriber.onCompleted();
                });
    }

    void sleep(Integer time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static List<Users> getUsers() {
        List<Users> users = new ArrayList<>();
        users.add(new Users("BOB", "Allen"));
        users.add(new Users("DOB", "Qllen"));
        users.add(new Users("COB", "Gllen"));
        users.add(new Users("OOB", "Pllen"));
        users.add(new Users("LOB", "Ollen"));
        users.add(new Users("SOB", "Rllen"));
        users.add(new Users("QOB", "Cllen"));
        return users;
    }
}


