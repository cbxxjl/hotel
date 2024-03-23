package com.cbxjl.hotel.system.domain.dto;

import com.cbxjl.hotel.common.enums.UserType;
import lombok.Data;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : cbxjl
 * @date : 2024/3/4 13:59
 */
@Data
public class test {
    private String name;

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10);
        test test = new test();
        test.wait();
        new ReentrantLock();
    }
}
