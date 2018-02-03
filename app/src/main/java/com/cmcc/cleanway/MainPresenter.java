package com.cmcc.cleanway;

import android.util.Log;

import com.juphoon.domain.entity.User;
import com.juphoon.domain.interactor.ClientGetAccount;
import com.juphoon.domain.interactor.ClientListenEvent;
import com.juphoon.domain.interactor.ClientLogin;
import com.juphoon.domain.interactor.DefaultObserver;
import com.juphoon.domain.interactor.UserGetFreeContacts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;

    UserGetFreeContacts userGetFreeContacts;

    private final ClientGetAccount clientGetAccount;

    ClientLogin clientLogin;

    ClientListenEvent clientListenEvent;

    @Inject
    MainPresenter(ClientGetAccount clientGetAccount) {
        this.clientGetAccount = clientGetAccount;
    }

    @Override
    public void takeView(MainContract.View view) {
        mView = view;
    }

    @Override
    public void destroy() {
        if (clientGetAccount != null) {
            clientGetAccount.dispose();
        }
    }

    @Override
    public void buttonClicked() {
//        getUserAccount();
//        testingFlatMapComplete();
        Observable<Long> observable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Long> e) throws Exception {
                e.onNext(1L);
                e.onNext(1L);
                e.onNext(1L);
                e.onNext(1L);
                e.onNext(1L);
            }
        });
        observable.switchMap(new Function<Long, ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> apply(@NonNull final Long aLong) throws Exception {
                return Observable.create(new ObservableOnSubscribe<Long>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Long> e) throws Exception {
                        e.onNext(aLong);
                        e.onComplete();
                    }
                });
            }
        }).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                log("subscribe.onNext");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                log("subscribe.onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                log("subscribe.onComplete");
            }
        });
    }

    private void testingFlatMapComplete() {
        Observable.just(1L, 2L, 3L)
                .flatMap(new Function<Long, ObservableSource<Long>>() {
                    @Override
                    public ObservableSource<Long> apply(@NonNull Long aLong) throws Exception {
                        return Observable.intervalRange(50, 10, 0, 1, TimeUnit.SECONDS);
                    }
                }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                log("testingFlatMapComplete.onNext:" + aLong);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                log("testingFlatMapComplete.onError:" + throwable);
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                log("testingFlatMapComplete.onComplete");
            }
        });
    }

    private void getFreeContactList() {
        userGetFreeContacts.execute(new DefaultObserver<List<User>>() {

            @Override
            public void onComplete() {
                log("getFreeContactList.onComplete");
                super.onComplete();
            }

            @Override
            public void onNext(@NonNull List<User> users) {
                log("getFreeContactList.onNext");
                super.onNext(users);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                log("getFreeContactList.onError");
            }
        }, null);
    }

    private void getUserAccount() {
        clientGetAccount.execute(new DefaultObserver<User>() {

            @Override
            public void onComplete() {
                log("getUserAccount.onComplete");
                super.onComplete();
            }

            @Override
            public void onNext(@NonNull User user) {
                log("getUserAccount.onNext");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                log("getUserAccount.onError");
            }
        }, null);
    }

    private void login(String userId) {
        clientLogin.execute(new DefaultObserver<String>() {

            @Override
            public void onComplete() {
                log("login.onComplete");
                super.onComplete();
            }

            @Override
            public void onNext(@NonNull String s) {
                log("login.onNext:" + s);
                super.onNext(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                log("login.onError");
            }
        }, ClientLogin.Param.forUser(userId));
    }

    private void multiCasting() {
        Observable<Long> longObservable = getLongObservable().share();

//        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS)
//                .doOnNext(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        log("observable emitter:" + aLong);
//                    }
//                })
//                .map(new Function<Long, Long>() {
//                    @Override
//                    public Long apply(@NonNull Long aLong) throws Exception {
//                        log("observable map:" + aLong);
//                        return aLong;
//                    }
//                })
//                .share();
        longObservable.subscribeOn(Schedulers.single()).observeOn(Schedulers.computation()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                log("observable subscribe 1:" + aLong);
            }
        });
        longObservable.subscribeOn(Schedulers.newThread()).observeOn(Schedulers.io()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                log("observable subscribe 2:" + aLong);
            }
        });
    }

    private void listenEvent() {
        clientListenEvent.execute(new DefaultObserver<String>() {

            @Override
            public void onNext(@NonNull String s) {
                log("listenEvent.onNext:" + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                log("listenEvent.onError:" + e);
            }
        }, null);
    }

    private void flowableTesting() {
        Flowable.intervalRange(0, 10, 0, 1, TimeUnit.SECONDS).subscribe(new DefaultSubscriber<Long>() {
            @Override
            public void onNext(Long aLong) {
                log("listenEvent.onNext:" + aLong);
            }

            @Override
            public void onError(Throwable t) {
                log("listenEvent.onError:" + t);
            }

            @Override
            public void onComplete() {
                log("listenEvent.onComplete");
            }
        });
    }

    private void mergeTesting() {
        Observable<Long> observable1 = Observable.just(1L, 2L, 3L);
        Observable<Long> observable2 = Observable.intervalRange(50, 10, 0, 1, TimeUnit.SECONDS);
        Observable.merge(observable1, observable2).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                log("mergeTesting.onNext:" + aLong);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                log("mergeTesting.onComplete");
            }
        });
    }

    private void zipTesting() {
        Observable<Long> observable1 = Observable.just(1L, 2L, 3L)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        log("zipTesting.observable1:next:" + aLong);
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        log("zipTesting.observable1:complete");
                    }
                }).subscribeOn(Schedulers.computation());
        Observable<Long> observable2 = Observable.intervalRange(50, 10, 0, 1, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        log("zipTesting.observable2:next:" + aLong);
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        log("zipTesting.observable2:complete");
                    }
                });
        Observable.combineLatest(observable2, observable1, new BiFunction<Long, Long, String>() {
            @Override
            public String apply(@NonNull Long aLong, @NonNull Long aLong2) throws Exception {
                return aLong + "and" + aLong2;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                log("zipTesting.onNext:" + s);
            }
        });
    }

    private void testCollect() {
        Observable.intervalRange(50, 10, 0, 1, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        log("testCollect.observable2:next:" + aLong);
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        log("testCollect.observable2:complete");
                    }
                }).collect(new Callable<List<Long>>() {
            @Override
            public List<Long> call() throws Exception {
                return new ArrayList<>();
            }
        }, new BiConsumer<List<Long>, Long>() {
            @Override
            public void accept(List<Long> longs, Long aLong) throws Exception {
                log("testCollect.observable2:BiConsumer");
                longs.add(aLong);
            }
        }).subscribe(new Consumer<List<Long>>() {
            @Override
            public void accept(List<Long> longs) throws Exception {
                for (Long aLong : longs) {
                    log("testCollect.Consumer:long" + aLong);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                log("testCollect.Consumer:error" + throwable);
            }
        });
    }

    private void testingConact() {
        Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onComplete();
            }
        }).doOnNext(new Consumer<String>() {
            @Override
            public void accept(String aLong) throws Exception {
                log("testingConact.observable1:next:" + aLong);
            }
        })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        log("testingConact.observable1:complete");
                    }
                });

        Observable<String> observable2 = Observable.just("1", "2", "3")
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String aLong) throws Exception {
                        log("testingConact.observable2:next:" + aLong);
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        log("testingConact.observable2:complete");
                    }
                }).subscribeOn(Schedulers.computation());

        Observable.concat(observable1, observable2).firstElement().subscribe(new Consumer<String>() {
            @Override
            public void accept(String serializable) throws Exception {
                log("testingConact.observable2:complete:" + serializable);
            }
        });
    }

    private Observable<Long> getLongObservable() {
        return Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Long> e) throws Exception {
                Observable.interval(1, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        log("longObservable:" + aLong);
                        e.onNext(aLong);
                    }
                });
            }
        });
    }

    private void log(String msg) {
        Log.d("RxJava", "Thread:" + Thread.currentThread().getName() + " message:" + msg);
    }
}
