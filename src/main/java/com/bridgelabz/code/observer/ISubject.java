package com.bridgelabz.code.observer;

public interface ISubject {
        public void attach(IObserver iObserver);
        public void detach(IObserver observer);
        public void notifyObservers();
}
