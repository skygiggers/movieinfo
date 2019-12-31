package rs.tafilovic.movieinfo.rest;

import java.security.PublicKey;

public class NetworkState {

    private Status status;

    public NetworkState(int status){
        this.status=new Status(status);
    }

    public NetworkState(int state, String message){
        this.status=new Status(state, message);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static class Status{
        int status;
        String message;

        public Status(int status){
            this.status=status;
        }

        public Status(int status, String message){
            this.status=status;
            this.message=message;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public static int FAILED=-1;
        public static int LOADING=0;
        public static int LOADED=1;
    }

    public static NetworkState FAILED=new NetworkState(Status.FAILED);
    public static NetworkState LOADING=new NetworkState(Status.LOADING);
    public static NetworkState LOADED=new NetworkState(Status.LOADED);
}
