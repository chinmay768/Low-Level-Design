package model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Group {
    private final String id;
    private final String name;
    private final List<User> members = new ArrayList<>();
    private final Map<User, BalanceSheet> balanceSheets = new HashMap<>();


    public Group(String id, String name) {

    }

}
