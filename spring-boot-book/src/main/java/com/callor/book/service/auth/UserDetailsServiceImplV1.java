package com.callor.book.service.auth;

// Ctrl + Alt + O : import 최적화
import com.callor.book.model.UserRole;
import com.callor.book.model.UserVO;
import com.callor.book.persistance.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class UserDetailsServiceImplV1 implements UserDetailsService {

    private final UserDao userDao;

    public UserDetailsServiceImplV1(UserDao userDao) {
        this.userDao = userDao;
    }

    /*
    설명주석 선생님 git에
     */
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.debug("USER SERVICE : {}",username);

        // findById() 는 Optional type VO 를 return 한다
        // Optional type 의 vo 에서 실제 UserVO 데이터를 Get 하기 위하여
        // orElse() 를 사용하는데
        // orElse() 매개변수로 blank UserVO 생성하려다 주었다
        // 만약 username 으로 select 를 했는데 데이터가 없으면
        // blank UserVO 얻게된다
        // null check 를 하지 않아도 안정한 코드가 된다
        UserVO userVO = userDao.findById(username).orElse(UserVO.builder().build());
        if(userVO.getUsername().equals(username)){
            throw new UsernameNotFoundException(username + "없음");
        }
        log.debug("로그인한 사용자 {}", userVO);

        List<GrantedAuthority> grantList = new ArrayList<>();

        Set<UserRole> roleList = userVO.getUserRoles();
        for(UserRole role : roleList) {
            log.debug("사용자 ROLE 정보 {}", roleList);
            // 문자열로 되어있는 ROLE 정보를
            // GrantedAuthority 정보로 변환하여 grantList 추가
            grantList.add(new SimpleGrantedAuthority(role.getRolename()));
        }
        userVO.setAuthorities(grantList);
        return userVO;
    }
}
