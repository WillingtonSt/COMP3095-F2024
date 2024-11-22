print('START');

db = db.getSiblingDB('product-service');

db.createUser(
    {
        user: 'app_user',
        pwd: 'password',
        roles: [ {role: 'readWrite', db: 'product-service'}]
    }
);

db.createCollection('user')

print('END');