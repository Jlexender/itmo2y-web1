package ru.lexender.ifmo.web1.fcgi;

import com.fastcgi.FCGIInterface;

public class FcgiInterfaceHolder {
    private static volatile FCGIInterface instance = null;

    public static FCGIInterface getInstance() {
        if (instance == null) {
            instance = new FCGIInterface();
        }
        return instance;
    }
}
