package DAO;

import Models.Likes;
import Models.Users;

import java.util.ArrayList;
import java.util.List;

class UsersDAOArray implements InterfaceDAO<Users> {
    private List<Users> usersDB = new ArrayList<>();


    public UsersDAOArray() {
        usersDB.add(new Users("Virginia!", "Supergirl", "https://vignette.wikia.nocookie.net/9b99c9b5-5597-45bb-97d4-5d7494c0f964/scale-to-width-down/1000"));
        usersDB.add(new Users("!!! Marianna !!!!", "Householder", "https://images.unsplash.com/photo-1508606572321-901ea443707f?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=aae2a05e1585a697cb891c202e68bb78&auto=format&fit=crop&w=1864&q=80"));
        usersDB.add(new Users("RoboGirl", "Servant", "https://robohash.org/68.186.255.198.png"));
    }

    @Override
    public void insert(Users users) {
        usersDB.add(users);
    }

    @Override
    public void update(Users obj) {
        throw new IllegalArgumentException("not implemented");
    }

    @Override
    public ArrayList<Users> get() {
        //Select all users w/o Likes
        ArrayList<Users> users = new ArrayList<>();
        for (Users userOne : usersDB) {
            //Check if do not liked yet
            boolean found = false;
            for (Likes likeOne : LikesDAOarray.getLikesDB()) {
                if (userOne.getUserId().equals(likeOne.getLikesLikedUserId())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                users.add(userOne);
            }
        }
        return users;
    }

    public ArrayList<Users> getWithLikes() {
        //Select all users with Likes
        ArrayList<Users> users = new ArrayList<>();
        for (Users userOne : usersDB) {
            //Check if do not liked yet
            for (Likes likeOne : LikesDAOarray.getLikesDB()) {
                if (userOne.getUserId().equals(likeOne.getLikesLikedUserId())) {
                    if (likeOne.getLinkesValue().equals("Like")) {
                        users.add(userOne);
                    }
                    break;
                }
            }
        }
        return users;
    }

    @Override
    public void delete(Users obj) {
        throw new IllegalArgumentException("not implemented");
    }

    public List<Users> getUsersDB() {
        return usersDB;
    }
}
