-- タグ
CREATE TABLE tags
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),   -- タグID
    name       VARCHAR(32) NOT NULL UNIQUE,                  -- タグ名
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 登録日時
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  -- 更新日時
);

-- 中間テーブル：レッスンに紐づくタグ
CREATE TABLE lesson_tags
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    lesson_id  UUID NOT NULL REFERENCES lessons (id) ON DELETE CASCADE, -- レッスンID
    tag_id     UUID NOT NULL REFERENCES tags (id) ON DELETE CASCADE,    -- タグID
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,            -- 登録日時
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,            -- 更新日時
    CONSTRAINT lesson_id_and_tag_id_constraint UNIQUE (lesson_id, tag_id)
);