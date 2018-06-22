package DAO;

import Models.Likes;
import Models.Users;

import java.util.ArrayList;
import java.util.List;

public class LikesDAOarray implements InterfaceDAO<Likes> {
    private static List<Likes> likesDB = new ArrayList<>();

    public static List<Likes> getLikesDB() {
        return likesDB;
    }

    @Override
    public void insert(Likes obj) {
        likesDB.add(obj);
    }

    @Override
    public void update(Likes obj) {
        throw new IllegalArgumentException("not implemented");
    }

    @Override
    public List<Users> get() {
        return null;
    }

    @Override
    public void delete(Likes obj) {
        throw new IllegalArgumentException("not implemented");
    }
}
