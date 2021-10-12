package com.lp.dao.factory;

import com.lp.dao.enums.ETypeDao;
import com.lp.dao.io.DaoFactoryIO;
import com.lp.dao.mysql.DaoFactoryMySQL;
import com.lp.exceptions.MorseDaoException;
import com.lp.interfaces.IDaoMorse;

public abstract class DaoFactory {

    
    /** 
     * @param target
     * @return DaoFactory
     * @throws MorseDaoException
     */
    public static DaoFactory getDaoFactory(ETypeDao target) throws MorseDaoException{
        switch(target){
            case IO:
                return new DaoFactoryIO();
            case MySQL:
                return new DaoFactoryMySQL();
            default:
                throw new MorseDaoException("target of type "+target+" impossible to create...");
        }

    }

    public abstract IDaoMorse getDaoMorse();
}
