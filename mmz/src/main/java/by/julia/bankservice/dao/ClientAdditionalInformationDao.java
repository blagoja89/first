package by.julia.bankservice.dao;

import by.julia.bankservice.entity.ClientAdditionalInformation;

/**
 * Created by julia on 04.05.2016.
 */
public class ClientAdditionalInformationDao extends AbstractDao<ClientAdditionalInformation> {
    @Override
    public Class<ClientAdditionalInformation> getEntityClass() {
        return ClientAdditionalInformation.class;
    }
}
