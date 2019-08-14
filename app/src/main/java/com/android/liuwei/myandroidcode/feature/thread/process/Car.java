package com.android.liuwei.myandroidcode.feature.thread.process;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Car implements Parcelable
{
    public static List<Car> newCars()
    {
        List<Car> list = new ArrayList<>(3);
        list.add(newBMW());
        list.add(newBENZ());
        list.add(newAudi());
        return list;
    }

    public static Car newBMW()
    {
        return new Car("BMW", "320Li", 30f);
    }

    public static Car newBENZ()
    {
        return new Car("BENZ", "C200L", 33f);
    }

    public static Car newAudi()
    {
        return new Car("AUDI", "A4L", 27f);
    }

    private String name;
    private String model;
    private float price;

    public Car(String name, String model, float price)
    {
        this.name = name;
        this.model = model;
        this.price = price;
    }

    public Car(Parcel in)
    {
        name = in.readString();
        model = in.readString();
        price = in.readFloat();
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public String getModel()
    {
        return model;
    }

    public float getPrice()
    {
        return price;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(name);
        dest.writeString(model);
        dest.writeFloat(price);
    }

    public static final Creator<Car> CREATOR = new Creator<Car>()
    {
        @Override
        public Car createFromParcel(Parcel in)
        {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size)
        {
            return new Car[size];
        }
    };

    @Override
    public String toString()
    {
        return String.format("{name=%s,model=%s,price=%s}", name, model, price);
    }
}
