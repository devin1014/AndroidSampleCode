// CarManager.aidl
package com.android.liuwei.myandroidcode.feature.thread.process;

// Declare any non-default types here with import statements
import com.android.liuwei.myandroidcode.feature.thread.process.Car;

interface CarManager {
    float getPrice(in Car car,float discount);
}
