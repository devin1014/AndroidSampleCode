// CarManager.aidl
package com.android.liuwei.myandroidcode.feature.process;

// Declare any non-default types here with import statements
import com.android.liuwei.myandroidcode.feature.process.Car;

interface CarManager {
    float getPrice(in Car car,float discount);
}
