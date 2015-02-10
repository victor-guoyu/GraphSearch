package search;

import java.util.EnumSet;
import java.util.HashMap;

public enum Cities {
    Oradea(0),
    Zerind(1),
    Arad(2),
    Timisoara(3),
    Lugoj(4),
    Mehadia(5),
    Drobeta(6),
    Sibiu(7),
    RimnicuVilcea(8),
    Craiova(9),
    Fagaras(10),
    Pitesti(11),
    Bucharest(12),
    Giurgiu(13),
    Urziceni(14),
    Neamt(15),
    Iasi(16),
    Vaslui(17),
    Hirsova(18),
    Eforie(19);

    private final int value;
    static final HashMap<String, Cities> nameToCity;
    static final HashMap<Integer, String> idToCityName;

    static {
        nameToCity = new HashMap<String, Cities>();
        idToCityName = new HashMap<Integer, String>();
        for (Cities city: EnumSet.allOf(Cities.class)) {
            nameToCity.put(city.name().toUpperCase(), city);
            idToCityName.put(city.getValue(), city.name());
        }
    }

    private Cities(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
