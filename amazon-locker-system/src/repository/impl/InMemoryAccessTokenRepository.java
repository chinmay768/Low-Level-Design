package repository.impl;

import model.AccessToken;
import repository.AccessTokenRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryAccessTokenRepository
        implements AccessTokenRepository {

    private final Map<String, AccessToken> map =
            new ConcurrentHashMap<>();

    @Override
    public AccessToken findByParcelId(String parcelId) {
        return map.get(parcelId);
    }

    @Override
    public void save(String parcelId,
                     AccessToken token) {

        map.put(parcelId, token);
    }

    @Override
    public void delete(String parcelId) {

        map.remove(parcelId);
    }
}
