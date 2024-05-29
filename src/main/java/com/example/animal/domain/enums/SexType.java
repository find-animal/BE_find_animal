package com.example.animal.domain.enums;

import java.util.HashMap;
import java.util.Map;

public enum SexType {
  M("수컷"), F("암컷"), Q("미상");

  private static final Map<String, SexType> BY_LABEL = new HashMap<>();

  static {
    for (SexType s : values()) {
      BY_LABEL.put(s.label, s);
    }
  }

  public final String label;

  private SexType(String label) {
    this.label = label;
  }

  public static SexType valueOfLabel(String label) {
    return BY_LABEL.get(label);
  }

}
