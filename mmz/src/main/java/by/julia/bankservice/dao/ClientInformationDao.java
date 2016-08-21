package by.julia.bankservice.dao;

import by.julia.bankservice.entity.ClientInformation;

/**
 * Created by julia on 04.05.2016.
 */
public class ClientInformationDao extends AbstractDao<ClientInformation> {
    @Override
    public Class<ClientInformation> getEntityClass() {
        return ClientInformation.class;
    }
}
