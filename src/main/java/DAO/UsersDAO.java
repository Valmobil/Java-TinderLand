package DAO;

import Models.Users;

import java.util.ArrayList;
import java.util.List;

public class UsersDAO extends AbstractDAO<Users> {
    static List<Users> usersProfiles = new ArrayList<>();
    private int userSize;

    @Override
    public void insert(Users users) {
        usersProfiles.add(users);
    }

    public int getUsersSize() {
        return usersProfiles.size();
    }

    @Override
    public void update(Users obj) {

    }

    @Override
    public Users get(Object id) {
        return null;
    }

    @Override
    public void delete(Object pk) {

    }
}
