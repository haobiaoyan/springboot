package com.hb.springboot.beans;

import com.alibaba.fastjson.JSON;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        OperationLog operationLog = new OperationLog();

       /* Date date = new Date();

        operationLog.setCreateTime(date);

        System.out.println(operationLog.getCreateTime().toString());
        date.setYear(5);

        System.out.println(operationLog.getCreateTime().toString());
*/
        Calendar calendar = Calendar.getInstance();
        System.out.println("1: " + calendar.getTime().toString());
        operationLog.setCreateTime(calendar.getTime());
        System.out.println("2: " + operationLog.getCreateTime().toString());

        calendar.set(Calendar.YEAR, 4000 + 1900);
        System.out.println("3: " + calendar.getTime().toString());
        System.out.println("4: " + operationLog.getCreateTime().toString());

        System.out.println(JSON.toJSONString(operationLog));

        String date = "2019-02-18 15:05:23";

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d = sf.parse(date);
            System.out.println(d.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 3);
        map.put(2, 5);
        map.put(1, 6);
        map.put(3, 6);
        map.put(4, 5);
        System.out.println(map);

        LinkedHashSet<Integer> set = new LinkedHashSet<Integer>();

        set.add(1);
        set.add(2);
        set.add(3);
        set.add(1);
        System.out.println(set);

        List<Car> cars = new ArrayList<>();

        Car car1 = new Test().new Car();
        car1.setId(1);
        car1.setName("大众");
        car1.setColor("red");

        car1.setTags(new ArrayList<>());

        cars.add(car1);

        Car car2 = new Test().new Car();
        car2.setId(1);
        car2.setName("丰田");
        car2.setColor("white");
        car2.setTags(new ArrayList<>());

        cars.add(car2);

        Car car3 = new Test().new Car();
        car3.setName("本田");
        car3.setColor("white");
        car3.setTags(new ArrayList<>());

        cars.add(car3);

        try {

            cars.stream().forEach(car -> car.getTags().stream().forEach(tag -> tag.setId(1)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("*****************************************8");
        System.out.println(1&1);//在销
        System.out.println(1&2);//停销
        System.out.println(1&4);//未上市
        System.out.println(1&0);
        System.out.println("*****************************************8");
        System.out.println(4&1);//在销
        System.out.println(4&2);//停销
        System.out.println(4&4);//未上市
        System.out.println(4&0);
        System.out.println("*****************************************8");
        System.out.println(2&1);//在销
        System.out.println(2&2);//停销
        System.out.println(2&4);//未上市
        System.out.println(2&0);
        System.out.println("*****************************************8");
        System.out.println(7&1);//在销
        System.out.println(7&2);//停销
        System.out.println(7&4);//未上市
        System.out.println(7&0);

        List<String> list = new ArrayList<>();

        list.add("5");
        list.add("8");
        list.add("3");
        list.add("1");
        list.add("9");
        list.add("1");
        //list.add("");

        System.out.println(list);
        list = list.stream().sorted(Comparator.comparingInt(Integer::parseInt)).collect(Collectors.toList());
        System.out.println("***" + list);
        List<String> list2 = new ArrayList<>();
        list.sort(String::compareTo);
        list2.sort(String::compareTo);
        System.out.println(list2);
        System.out.println(list);

        Set<String> set1 = new HashSet<>();
        set1.add("纯电");
        set1.add("插电混动");
        set1.add("");
        set1.add("燃油");
        set1.add("纯电");
        set1.add("纯电");
        set1.add("纯电");
        set1.add("");


        System.out.println(set1);
        System.out.println(set1.contains("纯电"));

        List<String> list1 = new ArrayList<>();

        list1.add("1");
        list1.add("2");

        list1.subList(0,1);

        System.out.println(list1);

        System.out.println("*******************************************");
        List<String> list3 = new ArrayList<>();
        list3.add("1");
        list3.add("2");
        list3.add("3");
        list3.add("5");
        list3.add("6");

        List<String> list4 = new ArrayList<>();
        list4.add("2");
        list4.add("3");
        list4.add("7");
        list4.add("8");

        //交集
        list3.stream().filter(s -> list4.contains(s)).collect(Collectors.toList());

        StringBuilder builder = new StringBuilder();
        //builder.append("2L").append("/");
        System.out.println(builder);
        if (!"".equals(builder.toString())){

            System.out.println(builder.toString().substring(0, builder.toString().lastIndexOf("/")));
        }
    }

    class Car {
        private int id;
        private String name;
        private String color;
        private List<Tag> tags;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public List<Tag> getTags() {
            return tags;
        }

        public void setTags(List<Tag> tags) {
            this.tags = tags;
        }

        class Tag {
            private String value;
            private int id;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
