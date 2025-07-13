package com.motorph.interfaces;

/**
 * Interface for authentication capabilities
 * Demonstrates Interface Segregation Principle
 */
public interface Authenticatable {
    boolean authenticate(String username, String password);
    boolean isAuthorized(String action);
    void logout();
}