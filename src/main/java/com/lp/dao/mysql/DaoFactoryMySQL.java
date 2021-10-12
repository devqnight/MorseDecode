package com.lp.dao.mysql;

import com.lp.dao.factory.DaoFactory;
import com.lp.interfaces.IDaoMorse;

public class DaoFactoryMySQL extends DaoFactory{
    
    /** 
     * @return IDaoMorse
     */
    @Override
    public IDaoMorse getDaoMorse(){
        throw new UnsupportedOperationException("Unsupported DAO");
    }
}
