package com.mid.base.fanxing;

public interface FXinterface<T> {
    public T next();
}

class FxClass implements FXinterface<String>{

    @Override
    public String next() {
        return null;
    }
}