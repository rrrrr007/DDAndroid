package com.diucity.dingding.persent;


//ViewModel实现
public interface DataBinder<T extends IDelegate, D> {

    void viewBindModel(T viewDelegate, D data);

    void work(T viewDelegate, Object object);


}
