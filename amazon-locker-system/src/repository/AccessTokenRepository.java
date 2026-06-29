package repository;

import model.AccessToken;

public interface AccessTokenRepository {

    AccessToken findByParcelId(String parcelId);

    void save(String parcelId,
              AccessToken token);

    void delete(String parcelId);
}
