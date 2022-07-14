package org.chengbing.controller;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.chengbing.entity.Cred;
import org.chengbing.entity.Namespace;
import org.chengbing.entity.Photo;
import org.chengbing.service.ICredService;
import org.chengbing.service.IPhotoService;
import org.chengbing.util.Result;
import org.chengbing.util.UserIdentity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

***REMOVED***
 * <p>
 *  前端控制器
 * </p>
 *
 * @author HaroldCI
 * @since 2022-06-24
***REMOVED***
@RestController
@RequestMapping("/cred")
public class CredController {

    @Resource
    ICredService service;

    @Resource
    IPhotoService photoService;

    @Resource
    UserIdentity verify;

    // CRUD API
    @PostMapping("/create")
    public Result<Integer> createNewAPI(HttpServletRequest request, @RequestBody Cred cred)
***REMOVED***
        Integer userId = verify.verifyUser(request);
        if (userId < 0)
            return new Result<>(null, 403);
        int res = service.createNewAPI(userId, cred);
        return res == 1 ? new Result<>(1, 200) : new Result<>(0, 400);
***REMOVED***
    @GetMapping("/query")
    public Result<List<Cred>> queryAPI(HttpServletRequest request)
***REMOVED***
        Integer userId = verify.verifyUser(request);
        if (userId < 0)
            return new Result<>(null, 403);
        List<Cred> returnList = service.queryAPI(userId);
        return returnList == null ? new Result<>(null, 400) : new Result<>(returnList, 200);
***REMOVED***

    @PostMapping("/update")
    public Result<Integer> updateAPI(HttpServletRequest request, @RequestBody Cred cred)
***REMOVED***
        Integer userId = verify.verifyUser(request);
        if (userId < 0)
            return new Result<>(null, 403);
        int res = service.updateAPI(userId, cred);
        return res == 1 ? new Result<>(res, 200) : new Result<>(res, 400);
***REMOVED***
    @PostMapping("/delete")
    public Result<Integer> deleteAPI(HttpServletRequest request, Integer credId)
***REMOVED***
        Integer userId = verify.verifyUser(request);
        if (userId < 0)
            return new Result<>(null, 403);
        int res = service.deleteAPI(userId, credId);
        return res == 1 ? new Result<>(res, 200) : new Result<>(res, 400);
***REMOVED***

    // CRD: Create (Update Photo), Query (Get Photo Address, bytes), Delete (Delete Photo)
    @PostMapping("/uploadPhoto/{token***REMOVED***")
    public String uploadPhoto(@PathVariable String token, MultipartFile file,@RequestParam String photo)
***REMOVED***
        Photo photo1 = JSONObject.parseObject(photo, Photo.class);
        Integer userId = service.getUserId(token);
        if (userId == null)
            return "Unidentified Token";
        if (service.getAuth(token).contains("C"))
            return photoService.insertPhotoRU(userId, file, photo1);
        else
            return "Wrong Auth for current Token";
***REMOVED***

    @PostMapping("/uploadPhotos/{token***REMOVED***")
    public List<String> uploadPhotos(@PathVariable String token, MultipartFile[] files,@RequestParam String photosStr)
***REMOVED***
        List<Photo> photos = JSONObject.parseObject(photosStr, new TypeReference<ArrayList<Photo>>(){***REMOVED***);
        Integer userId = service.getUserId(token);
        if (userId == null)
    ***REMOVED***
        if (service.getAuth(token).contains("C"))
            return photoService.insertPhotosRU(userId, files, photos);
        else
    ***REMOVED***
***REMOVED***

    @GetMapping(value = "/render/{token***REMOVED***", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] renderImage(@PathVariable String token, String uuid)
***REMOVED***
        Integer userId = service.getUserId(token);
        if (userId == null)
    ***REMOVED***
        if (service.getAuth(token).contains("R"))
            return service.render(userId, uuid);
        else
    ***REMOVED***
***REMOVED***

    @PostMapping("/deletePhoto/{token***REMOVED***")
    public Integer deletePhoto(@PathVariable String token, String uuid)
***REMOVED***
        Integer userId = service.getUserId(token);
        if (userId == null)
    ***REMOVED***
        if (service.getAuth(token).contains("D"))
            return service.deletePhoto(userId, uuid);
        else
            return -2;
***REMOVED***

    @GetMapping("/queryRootNamespace/{token***REMOVED***")
    public Namespace queryRootNamespace(@PathVariable String token)
***REMOVED***
        Integer userId = service.getUserId(token);
        if (userId == null)
    ***REMOVED***
        if (service.getAuth(token).contains("R"))
            return service.queryRootNamespace(userId);
        else
    ***REMOVED***
***REMOVED***

    @GetMapping("/queryNamespaces/{token***REMOVED***")
    public List<Namespace> queryNamespaces(@PathVariable String token, Integer parentId)
***REMOVED***
        Integer userId = service.getUserId(token);
        if (userId == null)
    ***REMOVED***
        if (service.getAuth(token).contains("R"))
            return service.queryNamespaces(userId, parentId);
        else
    ***REMOVED***
***REMOVED***
    @GetMapping("/queryPhotoList/{token***REMOVED***")
    public List<Photo> queryPhotoList(@PathVariable String token, Integer nsId)
***REMOVED***
        Integer userId = service.getUserId(token);
        if (userId == null)
    ***REMOVED***
        if (service.getAuth(token).contains("R"))
            return service.queryPhotoList(userId, nsId);
        else
    ***REMOVED***
***REMOVED***
***REMOVED***

