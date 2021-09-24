package com.lp.dao.io;

import com.lp.dao.factory.DaoFactory;
import com.lp.interfaces.IDaoMorse;

public class DaoFactoryIO extends DaoFactory{
    private IDaoMorse daoMorse;

    @Override
    public IDaoMorse getDaoMorse(){
        if(this.daoMorse == null){
            this.daoMorse = DaoMorse.getDaoMorseInstance();
        }
        return this.daoMorse;
    }
}
