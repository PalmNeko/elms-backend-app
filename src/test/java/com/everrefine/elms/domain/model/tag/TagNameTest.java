package com.everrefine.elms.domain.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.everrefine.elms.domain.exception.InvalidValueException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TagNameTest {

  @Nested
  class タグ名作成 {

    @Test
    void 前後がトリムされたタグ名が作成される() {
      // 全角空白もトリムされる
      final TagName tagName = new TagName(" Java　 ");
      assertEquals("Java", tagName.value());
    }

    @Test
    void 文字数が32文字は作成できる() {
      final String name = "あ".repeat(32);
      final TagName tagName = new TagName(name);
      assertEquals(32, tagName.value().length());
    }

    @Test
    void 文字数が32文字を超えるとInvalidValueExceptionを投げる() {
      assertThrows(InvalidValueException.class, () -> new TagName("あ".repeat(33)));
    }

    @Test
    void タグを指定しない場合InvalidValueExceptionを投げる() {
      // nullの場合 - エラーを投げる
      assertThrows(InvalidValueException.class, () -> new TagName(null));

      // 空文字列の場合 - エラーを投げる
      assertThrows(InvalidValueException.class, () -> new TagName(""));

      // 空白文字（全角含む）のみの場合 - エラーを投げる
      assertThrows(InvalidValueException.class, () -> new TagName(" \r\n　 "));
    }
  }
}
