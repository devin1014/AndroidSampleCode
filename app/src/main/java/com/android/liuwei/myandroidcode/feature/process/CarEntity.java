package com.android.liuwei.myandroidcode.feature.process;

import android.os.Parcel;
import android.os.Parcelable;

public class CarEntity implements Parcelable
{
    public static CarEntity newBMW()
    {
        return new CarEntity("BMW", "320Li", 0f);
    }

    public static CarEntity newBENZ()
    {
        return new CarEntity("BENZ", "C200L", 0f);
    }

    public static CarEntity newAudi()
    {
        return new CarEntity("AUDI", "A4L", 0f);
    }

    private String name;
    private String model;
    private float price;

    public CarEntity(String name, String model, float price)
    {
        this.name = name;
        this.model = model;
        this.price = price;
    }

    public CarEntity(Parcel in)
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

    public static final Creator<CarEntity> CREATOR = new Creator<CarEntity>()
    {
        @Override
        public CarEntity createFromParcel(Parcel in)
        {
            return new CarEntity(in);
        }

        @Override
        public CarEntity[] newArray(int size)
        {
            return new CarEntity[size];
        }
    };

    @Override
    public String toString()
    {
        return String.format("{name=%s,model=%s,price=%s}", name, model, price);
    }
}
