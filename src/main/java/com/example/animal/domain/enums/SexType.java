package com.example.animal.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SexType {
  M("수컷"), F("암컷"), Q("미상");

  public final String label;

}
