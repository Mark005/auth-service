-- changeset Mark:202304201050-1
INSERT INTO authority_group (id, group_tag, name, description)
VALUES (gen_random_uuid(), 'FULL_ACCESS', 'Full access', 'Full access for all service features');

INSERT INTO authority_group (id, group_tag, name, description)
VALUES (gen_random_uuid(), 'CUSTOMER', 'Customer', 'Authorities for self registered regular customer');


INSERT INTO authority (id, authority, description)
VALUES (gen_random_uuid(), 'user:read', 'Access to read users');

INSERT INTO authority_group_authorities (authority_group_id, authorities_id)
VALUES ((SELECT id FROM authority_group WHERE group_tag = 'CUSTOMER'),
        (SELECT id FROM authority WHERE authority = 'user:read'));




