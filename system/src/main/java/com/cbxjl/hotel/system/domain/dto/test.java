package com.cbxjl.hotel.system.domain.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author : cbxjl
 * @date : 2024/3/4 13:59
 */
@Data
public class test {
    private String name;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(mySort(arr, 0, arr.length - 1));
    }

    public static int mySort(int[] arr, int left, int right) {
        int count = 0;
        if (left <
                right) {
            int mid = (left + right) / 2;
            count += mySort(arr, left, mid);
            count += mySort(arr, mid + 1, right);
            count += myCount(arr, left, mid, right);
        }

        return count;
    }

    public static int myCount(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int count = 0;
        int index = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[index++] = arr[i++];
            } else {
                temp[index++] = arr[j++];
                count += mid - i + 1;
            }
        }

        while (i <= mid) {
            temp[index++] = arr[i++];
        }

        while (j <= right) {
            temp[index++] = arr[j++];
        }
        System.arraycopy(temp, 0, arr, left, temp.length);
        return count;
    }
}
