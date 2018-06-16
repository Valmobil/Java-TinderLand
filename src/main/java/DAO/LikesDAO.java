package DAO;

import Models.Likes;
import Models.Users;

import java.util.ArrayList;
import java.util.List;




public class LikesDAO extends AbstractDAO {
    public static List<Likes> likes;

    public LikesDAO() {
        likes = new ArrayList<>();
    }

    @Override
    public void insert(Object like) {
        likes.add((Likes) like);
    }

    @Override
    public void update(Object obj) {

    }

    @Override
    public Object get(Object pk) {
        return null;
    }

    @Override
    public void delete(Object pk) {

    }
}
