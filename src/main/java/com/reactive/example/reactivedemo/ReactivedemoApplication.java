package com.reactive.example.reactivedemo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ReactivedemoApplication {
    // Main Method
    public static void main(String[] args) {

        Observable<Users> observable = new ReactivedemoApplication().getData(getUsers());
        observable.subscribe(System.out::println, // Callback from Subscribe.
                throwable -> System.out.println(" Excpetion:- " + throwable),
                () -> System.out.println("Completed")
        );
    }

    // Observable Interface to the Model.
    Observable<Users> getData(final List<Users> usersList) {
        // Creating an Observable Pattern with a Subscriber.
        return Observable.create(
                subscriber -> {
                    if (!subscriber.isUnsubscribed()) {
                        usersList.stream().forEach(
                                users -> {
                                    subscriber.onNext(users);
                                    sleep(10000);
                                    subscriber.onError(new RuntimeException("Something Wrong!"));
                                }
                        );
                    }
                    subscriber.onCompleted();
                });
    }

    // Thread Sleep
    void sleep(Integer time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Static List
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


