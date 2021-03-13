package toyproject.springteam.controller;

import com.amazonaws.*;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Path;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;

@Slf4j
@RequiredArgsConstructor // final 멤버변수가 있으면 생성자 항목에 포함시킴
@Component
@PropertySource("classpath:configure.properties")
public class S3PresignedURL {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    public void downloadS3(String directory, MultipartFile multipartFile) throws IOException, IllegalStateException {

        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withClientConfiguration(clientConfig)
                    .withRegion(region)
                    .withPathStyleAccessEnabled(true)
                    .build();

            Date expiration = new Date();
            long expTimeMillis = expiration.getTime();
            expTimeMillis += 1000 * 60 * 60;
            expiration.setTime(expTimeMillis);

            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, "blue.jpg");
            generatePresignedUrlRequest.setMethod(HttpMethod.PUT);
            generatePresignedUrlRequest.setExpiration(expiration);
            generatePresignedUrlRequest.setContentType("image/jpeg");

            generatePresignedUrlRequest.addRequestParameter(Headers.S3_CANNED_ACL,
                    CannedAccessControlList.PublicRead.toString());

            URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
            System.out.println("Pre-Signed URL : " + url.toString());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("PUT");
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-type", "image/jpeg");

            File file = new File(multipartFile.getOriginalFilename());
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();

            /*byte[] fileBytes = Files.readAllBytes(file.toPath());
            OutputStream output = connection.getOutputStream();
            InputStream input = new ByteArrayInputStream(fileBytes);
            byte[] buffer = new byte[4096];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            output.flush();
            output.close();*/

            OutputStream out = connection.getOutputStream();
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[5000];
            int readCount = 0;
            while((readCount = fis.read(buffer)) != -1) {
                out.write(buffer, 0, readCount);
            }
            out.flush();
            out.close();

            // local에 저장되는 이미지 삭제
            if (file != null) file.delete();

            connection.getResponseCode();
            System.out.println("HTTP response code: " + connection.getResponseCode());

            S3Object object = s3Client.getObject(bucket, "blue.jpg");
            System.out.println("Object " + object.getKey() + " created in bucket " + object.getBucketName());

        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }

    }
}
