package com.android.liuwei.myandroidcode.feature.process.car;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class Car implements Parcelable
{
    private final String name;
    private final String model;
    private final float price;

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

    @NonNull
    @Override
    public String toString()
    {
        return String.format("{name=%s,model=%s,price=%s}", name, model, price);
    }

    public static class BMW extends Car
    {
        public static BMW instance()
        {
            return new BMW("BMW", "320Li", 30f);
        }

        public BMW(String name, String model, float price)
        {
            super(name, model, price);
        }

        public BMW(Parcel in)
        {
            super(in);
        }
    }

    public static class BENZ extends Car
    {
        public static BENZ instance()
        {
            return new BENZ("BENZ", "C200L", 33f);
        }

        public BENZ(String name, String model, float price)
        {
            super(name, model, price);
        }

        public BENZ(Parcel in)
        {
            super(in);
        }
    }

    public static class AUDI extends Car
    {
        public static AUDI instance()
        {
            return new AUDI("AUDI", "A4L", 27f);
        }

        public AUDI(String name, String model, float price)
        {
            super(name, model, price);
        }

        public AUDI(Parcel in)
        {
            super(in);
        }
    }
}
