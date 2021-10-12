package com.lp.dao.io;

import com.lp.dao.factory.DaoFactory;
import com.lp.interfaces.IDaoMorse;

public class DaoFactoryIO extends DaoFactory{
    private IDaoMorse daoMorse;

    
    /** 
     * Returns a DAO instance
     * @return IDaoMorse : resulting instance of DAO
     */
    @Override
    public IDaoMorse getDaoMorse(){
        if(this.daoMorse == null){
            this.daoMorse = DaoMorse.getDaoMorseInstance();
        }
        return this.daoMorse;
    }
}
