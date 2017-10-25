package com.example.shinelon.myutils.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Peng on 2017/10/25.
 */

public class Resume implements Serializable {
    private int id;
    private String name;
    private String province;
    private String city;

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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    /**
     * 我的食物
     */
    private MyFood myFood;

    public MyFood getMyFood() {
        return myFood;
    }

    public void setMyFood(MyFood myFood) {
        this.myFood = myFood;
    }

    public static class MyFood implements Serializable{
        /**
         * 食物种类
         */
        private String type;
        /**
         * 食物列表
         */
        private ArrayList<FoodListBean> foodListBeen;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public ArrayList<FoodListBean> getFoodListBeen() {
            return foodListBeen;
        }

        public void setFoodListBeen(ArrayList<FoodListBean> foodListBeen) {
            this.foodListBeen = foodListBeen;
        }

        /**
         * 食物相关信息
         */
        public static class FoodListBean implements Serializable{
            private String foodName;//食物名称
            private String foodImg;//食物图片

            public String getFoodName() {
                return foodName;
            }

            public void setFoodName(String foodName) {
                this.foodName = foodName;
            }

            public String getFoodImg() {
                return foodImg;
            }

            public void setFoodImg(String foodImg) {
                this.foodImg = foodImg;
            }
        }
    }
}
