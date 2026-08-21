-- タグ
CREATE TABLE tags
(
    id   UUID PRIMARY KEY DEFAULT gen_random_uuid(), -- タグID
    name VARCHAR(32) NOT NULL UNIQUE                -- タグ名
);

-- 中間テーブル：レッスンに紐づくタグ
CREATE TABLE lesson_tags
(
    lesson_id UUID REFERENCES lessons (id) ON DELETE CASCADE, -- レッスンID
    tag_id   UUID REFERENCES tags (id) ON DELETE CASCADE,     -- タグID
    CONSTRAINT lesson_id_and_tag_id_constraint UNIQUE (lesson_id, tag_id)
);