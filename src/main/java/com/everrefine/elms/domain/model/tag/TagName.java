package com.everrefine.elms.domain.model.tag;

import com.everrefine.elms.domain.exception.InvalidValueException;

/** タグ名の値オブジェクト */
public record TagName(String value) {

  // 最大文字数
  private static final int MAX_LENGTH = 32;

  /**
   * タグ名を作成する。
   *
   * @param value タグ名文字列。（null、空文字列、空白文字列でない。）（32文字以内。）
   */
  public TagName {
    value = value != null ? value.strip() : null;
    if (value == null || value.isEmpty()) {
      throw new InvalidValueException("タグ名を入力してください");
    }
    if (value.length() > MAX_LENGTH) {
      throw new InvalidValueException("タグ名は" + MAX_LENGTH + "文字以内で入力してください");
    }
  }
}
