package top.itshanhe.newcodevideo.web.service.impl;

import top.itshanhe.newcodevideo.web.entity.JwtTokens;
import top.itshanhe.newcodevideo.web.mapper.JwtTokensMapper;
import top.itshanhe.newcodevideo.web.service.IJwtTokensService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * JWT token令牌表 服务实现类
 * </p>
 *
 * @author shanhe
 * @since 2024-03-27
 */
@Service
public class JwtTokensServiceImpl extends ServiceImpl<JwtTokensMapper, JwtTokens> implements IJwtTokensService {

}
