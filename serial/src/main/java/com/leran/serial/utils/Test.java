package com.leran.serial.utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
            List<Col>  reg = new ArrayList<>();
            List<Col> actual = new ArrayList<>();

            actual.removeAll(reg);
    }

    public class Col{
        //字段名
        private String colName;
        //字段类型
        private String colType;

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            Col col = (Col) obj;
            return this.colName.equals(col.colName)
                    && this.colType.equals(col.colType);
        }

        @Override
        public int hashCode() {
            return Objects.hash(colName, colType);
        }

        @Override
        public String toString() {
            return "Col{" +
                    "colName='" + colName + '\'' +
                    ", colType='" + colType + '\'' +
                    '}';
        }
    }

}
