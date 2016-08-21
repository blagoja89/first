package by.julia.bankservice.dao;

import by.julia.bankservice.entity.PassportData;

/**
 * Created by julia on 04.05.2016.
 */
public class PassportDataDao extends AbstractDao<PassportData> {
    @Override
    public Class<PassportData> getEntityClass() {
        return PassportData.class;
    }
}
