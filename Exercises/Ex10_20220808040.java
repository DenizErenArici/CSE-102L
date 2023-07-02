import java.util.*;
import java.util.PriorityQueue;

public class Ex10_20220808040 {

}
class User {
    private int id;
    private String username;
    private String email;
    private Set<User> followers;
    private Set<User> following;
    private Set<Post> likedPosts;
    private Map<User, Queue<Message>> messages;

    public User(String username, String email) {
        this.id = hashCode();
        this.username = username;
        this.email = email;
        this.followers = new HashSet<>();
        this.following = new HashSet<>();
        this.likedPosts = new HashSet<>();
        this.messages = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public Set<Post> getLikedPosts() {
        return likedPosts;
    }

    public void message(User recipient, String content) {
        Message message = new Message(this, content);
        if (!messages.containsKey(recipient)) {
            this.messages.put(recipient, new PriorityQueue<>());
            recipient.messages.put(recipient, new PriorityQueue<>());
        }
        this.messages.get(recipient).add(message);
        recipient.messages.get(this).add(message);
        read(recipient);
    }
    public void read(User user){
        for(Message message:messages.get(user)){
            System.out.println(message);
        }
    }
    public void follow(User user){
        if(!following.contains(user)){
            this.following.add(user);
            user.followers.add(this);
        }else{
         this.following.remove(user);
         user.followers.remove(this);
        }
    }
    public void like(Post post){
        if(!likedPosts.contains(post)){
            this.likedPosts.add(post);
            post.likedBy(this);
        }else{
            this.likedPosts.remove(post);
            post.likedBy(this);
        }
    }
    public Post post(String content){
        Post post=new Post(content);
        return post;
    }
    public Comment comment(Post post,String content){
        Comment comment=new Comment(content);
        post.commentBy(this,comment);
        return comment;
    }
    @Override
    public boolean equals(Object o){
        if (o instanceof User){
            User user = (User) o;
            return this.id==user.id;
        } else{
            return false;
        }
    }
    @Override
    public int hashCode(){
        return Objects.hash(email);
    }


}
class Message{
    private boolean seen;
    private Date dateSent;
    private String content;
    private User sender;

    public Message(User sender,String content){
      this.sender=sender;
      this.content=content;
      this.dateSent= new Date();
      this.seen=false;
    }
    public String read(User reader){
        if(sender!=reader){
            seen=true;
        }
        System.out.println("Sent at:"+dateSent);
        return content;
    }
    public boolean hasRead(){
        return seen;
    }
}
class Post{
    private Date datePosted;
    private String content;
    private Set<User> likes;
    private Map<User, List<Comment>> comments;
    public Post(String content) {
        this.content = content;
        this.datePosted = new Date();
        this.likes = new HashSet<>();
        this.comments = new HashMap<>();
    }
    public boolean likedBy(User user){
        if (likes.add(user)){
            return true;
        } else{
            likes.remove(user);
            return false;
        }
    }
   public boolean commentBy(User user,Comment comment){
        if(!comments.containsKey(user)){
            comments.put(user,new ArrayList<>());
        }
        return comments.get(user).add(comment);
   }
    public String getContent() {
        System.out.println("Posted at: " + datePosted);
        return content;
    }
    public Comment getComment(User user,int index){
        if(comments.containsKey(user)){
            List<Comment> usersComments = comments.get(user);
            if(index >= 0 && index < usersComments.size()){
                return usersComments.get(index);
            }
        }
        return null;
    }
    public int getCommentCount(){
        int total=0;
        for(List<Comment> usersComments : comments.values()){
            total+=usersComments.size();
        }
        return total;
    }
    public int getCommentCountByUser(User user) {
        int total = 0;
        List<Comment> usersComments = comments.get(user);
        if (usersComments != null) {
            total += usersComments.size();
        }
        return total;
    }
}
class Comment extends Post{
    public Comment(String message) {
        super(message);
    }
}
class SocialNetwork{
    private static Map<User, List<Post>> postsByUsers = new HashMap<>();
    public static User register(String username, String email) {
        User user = new User(username, email);
        if (!postsByUsers.containsKey(user)) {
            postsByUsers.put(user, new ArrayList<>());
            return user;
        } else {
            return null;
        }
    }
    public static Post post(User user, String content) {
        if (postsByUsers.containsKey(user)) {
            Post post = new Post(content);
            List<Post> posts = postsByUsers.get(user);
            posts.add(post);
            return post;
        }
        return null;
    }
    public static User getUser(String email) {
        for (User user : postsByUsers.keySet()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
    public static HashSet<Post> getfeed(User user){
        HashSet<Post> allOfPosts=new HashSet<>();
        for(User followed: postsByUsers.keySet()){
            if(user.getFollowing().contains(followed)){
                ArrayList<Post> userPosts= (ArrayList<Post>) postsByUsers.get(followed);
              for(Post userPost:userPosts){
                  allOfPosts.add(userPost);
                  return allOfPosts;
              }
            }
        }
        return null;
    }
    public static Map<User,String> search(String keyword){
        Map<User,String> map=new HashMap<>();
        for(User user: postsByUsers.keySet()){
            String userName=user.getUsername();
            if(userName.contains(keyword)){
                map.put(user,userName);
            }
        }
        return map;
    }
    public static <K,V> Map<V,Set<K>> reverseMap(Map<K,V> map){
        Map<V,Set<K>> reverse=new HashMap<>();
        for(K key:map.keySet()){
            if (reverse.containsKey(map.get(key))){
                reverse.get(map.get(key)).add(key);
            }else{
                Set<K> inset=new HashSet<>();
                inset.add(key);
                reverse.put(map.get(key),inset);
            }
        }
        return reverse;
    }

}





















