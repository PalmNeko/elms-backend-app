package com.everrefine.elms.domain.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.everrefine.elms.domain.exception.InvalidValueException;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TagCollectionTest {

  private static List<Tag> tags(int count) {
    return IntStream.range(0, count).mapToObj(i -> Tag.create("tag" + i)).toList();
  }

  @Nested
  class タグコレクション作成 {

    @Test
    void 同名のタグは先頭のものだけが残る() {
      TagCollection collection =
          TagCollection.create(List.of(Tag.create("Java"), Tag.create("Java"), Tag.create("Go")));
      assertEquals(List.of("Java", "Go"), collection.nameValues());
    }

    @Test
    void タグが50個は作成できる() {
      TagCollection collection = TagCollection.create(tags(50));
      assertEquals(50, collection.values().size());
    }

    @Test
    void タグが50個を超えるとInvalidValueExceptionを投げる() {
      assertThrows(InvalidValueException.class, () -> TagCollection.create(tags(51)));
    }

    @Test
    void 重複を除いて50個以内なら作成できる() {
      List<Tag> withDuplicates =
          IntStream.range(0, 100).mapToObj(i -> Tag.create("tag" + (i % 50))).toList();
      TagCollection collection = TagCollection.create(withDuplicates);
      assertEquals(50, collection.values().size());
    }
  }
}
