package DAO;

import Models.Users;

import java.util.ArrayList;
import java.util.List;

public class UsersDAO extends AbstractDAO<Users> {
    private static List<Users> users;

    public UsersDAO() {
        users = new ArrayList<>();
        users.add(new Users("Virginia!", "https://vignette.wikia.nocookie.net/9b99c9b5-5597-45bb-97d4-5d7494c0f964/scale-to-width-down/1000"));
        users.add(new Users("!!! Marianna !!!!", "https://images.unsplash.com/photo-1508606572321-901ea443707f?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=aae2a05e1585a697cb891c202e68bb78&auto=format&fit=crop&w=1864&q=80"));
    }

    @Override
    public void insert(Users users) {
        UsersDAO.users.add(users);
    }

    public int getUsersSize() {
        return users.size();
    }

    @Override
    public void update(Users obj) {

    }

    @Override
    public Users get(Object index) {
        return users.get((int) index);
    }

    @Override
    public void delete(Object pk) {

    }
}
