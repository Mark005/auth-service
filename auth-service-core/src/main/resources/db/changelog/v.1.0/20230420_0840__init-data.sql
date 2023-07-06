-- changeset Mark:202304201050-1

-- create authority_groups
INSERT INTO authority_group (id, group_tag, name, description)
VALUES (gen_random_uuid(), 'FULL_ACCESS', 'Full access', 'Full access for all service features');

INSERT INTO authority_group (id, group_tag, name, description)
VALUES (gen_random_uuid(), 'CUSTOMER', 'Customer', 'Authorities for self registered regular customer');

INSERT INTO authority_group (id, group_tag, name, description)
VALUES (gen_random_uuid(), 'PRODUCT_MANAGER', 'Product Manager', 'Authorities to manage products');

-- create authorities
INSERT INTO authority (id, authority, description)
VALUES (gen_random_uuid(), 'security-user:read', 'Access to read security users');

INSERT INTO authority (id, authority, description)
VALUES (gen_random_uuid(), 'authority:read', 'Access to read authorities');

INSERT INTO authority (id, authority, description)
VALUES (gen_random_uuid(), 'user-authority:update', 'Access to change security user authorities and authority groups');

INSERT INTO authority (id, authority, description)
VALUES (gen_random_uuid(), 'user:read', 'Access to read users');

INSERT INTO authority (id, authority, description)
VALUES (gen_random_uuid(), 'category:create', 'Access to create product category');
INSERT INTO authority (id, authority, description)
VALUES (gen_random_uuid(), 'category:update', 'Access to update product category');
INSERT INTO authority (id, authority, description)
VALUES (gen_random_uuid(), 'category:delete', 'Access to delete product category');
INSERT INTO authority (id, authority, description)

VALUES (gen_random_uuid(), 'product-item:read', 'Access to read product item');
INSERT INTO authority (id, authority, description)
VALUES (gen_random_uuid(), 'product-item:create', 'Access to create product item');
INSERT INTO authority (id, authority, description)
VALUES (gen_random_uuid(), 'product-item:update', 'Access to update product item');
INSERT INTO authority (id, authority, description)
VALUES (gen_random_uuid(), 'product-item:delete', 'Access to delete product item');

INSERT INTO authority (id, authority, description)
VALUES (gen_random_uuid(), 'delivery-type:create', 'Access to create new delivery type');
INSERT INTO authority (id, authority, description)
VALUES (gen_random_uuid(), 'delivery-type:update', 'Access to update delivery type, enable/disable');

INSERT INTO authority (id, authority, description)
VALUES (gen_random_uuid(), 'email-account:read', 'Access to read email account for sending notifications');
INSERT INTO authority (id, authority, description)
VALUES (gen_random_uuid(), 'email-account:create', 'Access to create email account for sending notifications');
INSERT INTO authority (id, authority, description)
VALUES (gen_random_uuid(), 'email-account:update', 'Access to update email account for sending notifications');
INSERT INTO authority (id, authority, description)
VALUES (gen_random_uuid(), 'email-account:delete', 'Access to delete email account for sending notifications');


-- bing CUSTOMER authority_group with authorities

-- bing PRODUCT_MANAGER authority_group with authorities
INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'category:create'),
        (SELECT id FROM authority_group WHERE group_tag = 'PRODUCT_MANAGER'));
INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'category:update'),
        (SELECT id FROM authority_group WHERE group_tag = 'PRODUCT_MANAGER'));
INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'category:delete'),
        (SELECT id FROM authority_group WHERE group_tag = 'PRODUCT_MANAGER'));

INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'product-item:read'),
        (SELECT id FROM authority_group WHERE group_tag = 'PRODUCT_MANAGER'));
INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'product-item:create'),
        (SELECT id FROM authority_group WHERE group_tag = 'PRODUCT_MANAGER'));
INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'product-item:update'),
        (SELECT id FROM authority_group WHERE group_tag = 'PRODUCT_MANAGER'));
INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'product-item:delete'),
        (SELECT id FROM authority_group WHERE group_tag = 'PRODUCT_MANAGER'));


-- bing FULL_ACCESS authority_group with authorities
INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'security-user:read'),
        (SELECT id FROM authority_group WHERE group_tag = 'FULL_ACCESS'));

INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'authority:read'),
        (SELECT id FROM authority_group WHERE group_tag = 'FULL_ACCESS'));

INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'user-authority:update'),
        (SELECT id FROM authority_group WHERE group_tag = 'FULL_ACCESS'));

INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'user:read'),
        (SELECT id FROM authority_group WHERE group_tag = 'FULL_ACCESS'));

INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'category:create'),
        (SELECT id FROM authority_group WHERE group_tag = 'FULL_ACCESS'));
INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'category:update'),
        (SELECT id FROM authority_group WHERE group_tag = 'FULL_ACCESS'));
INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'category:delete'),
        (SELECT id FROM authority_group WHERE group_tag = 'FULL_ACCESS'));

INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'product-item:read'),
        (SELECT id FROM authority_group WHERE group_tag = 'FULL_ACCESS'));
INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'product-item:create'),
        (SELECT id FROM authority_group WHERE group_tag = 'FULL_ACCESS'));
INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'product-item:update'),
        (SELECT id FROM authority_group WHERE group_tag = 'FULL_ACCESS'));
INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'product-item:delete'),
        (SELECT id FROM authority_group WHERE group_tag = 'FULL_ACCESS'));

INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'delivery-type:create'),
        (SELECT id FROM authority_group WHERE group_tag = 'FULL_ACCESS'));
INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'delivery-type:update'),
        (SELECT id FROM authority_group WHERE group_tag = 'FULL_ACCESS'));

INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'email-account:read'),
        (SELECT id FROM authority_group WHERE group_tag = 'FULL_ACCESS'));
INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'email-account:create'),
        (SELECT id FROM authority_group WHERE group_tag = 'FULL_ACCESS'));
INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'email-account:update'),
        (SELECT id FROM authority_group WHERE group_tag = 'FULL_ACCESS'));
INSERT INTO authority_group_authorities (authorities_id, authority_group_id)
VALUES ((SELECT id FROM authority WHERE authority = 'email-account:delete'),
        (SELECT id FROM authority_group WHERE group_tag = 'FULL_ACCESS'));

