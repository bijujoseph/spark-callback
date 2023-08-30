
1. create a role `EMRServerlessS3RuntimeRole`
```shell
aws iam create-role \
    --role-name EMRServerlessS3RuntimeRole \
    --assume-role-policy-document file://emr-serverless-trust-policy.json \
    --profile auth
    
    role ARN :
arn:aws:iam::168059647973:role/EMRServerlessS3RuntimeRole    

```
2. Create Policy with permissions to read assembly jar and read/write to input output bucket
```shell 
aws iam create-policy \
    --policy-name EMRServerlessS3AndGlueAccessPolicy \
    --policy-document file://emr-sample-access-policy.json
    
Policy ARN:
arn:aws:iam::168059647973:policy/EMRServerlessS3AndGlueAccessPolicy    
```

3. Attach the policy to the role 
```shell
aws iam attach-role-policy \
    --role-name EMRServerlessS3RuntimeRole \
    --policy-arn arn:aws:iam::168059647973:policy/EMRServerlessS3AndGlueAccessPolicy
```