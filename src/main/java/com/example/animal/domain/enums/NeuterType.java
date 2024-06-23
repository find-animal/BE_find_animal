package com.example.animal.domain.enums;

import java.util.HashMap;
import java.util.Map;

public enum NeuterType {
  Y("시행"), N("미시행"), U("미상");

  private static final Map<String, NeuterType> BY_LABEL = new HashMap<>();

  static {
    for (NeuterType n : values()) {
      BY_LABEL.put(n.label, n);
    }
  }

  public final String label;

  private NeuterType(String label) {
    this.label = label;
  }

  public static NeuterType valueOfLabel(String label) {
    return BY_LABEL.get(label);
  }
}
