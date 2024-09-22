<script setup>
import { ref } from 'vue'
const userInfo = ref({
    id: 0,
    username: 'zhangsan',
    nickname: 'zs',
    email: 'zs@163.com',
})
const rules = {
    nickname: [
        { required: true, message: '请输入用户昵称', trigger: 'blur' },
        {
            pattern: /^\S{2,10}$/,
            message: '昵称必须是2-10位的非空字符串',
            trigger: 'blur'
        }
    ],
    email: [
        { required: true, message: '请输入用户邮箱', trigger: 'blur' },
        { type: 'email', message: '邮箱格式不正确', trigger: 'blur' }
    ]
}
// 提交修改
import { userUpdateService, userInfoGetService } from '@/api/user';
import { userInfoStore } from '@/store/user';
import { ElMessage } from 'element-plus';
const infoStore = userInfoStore()
const submit = async() => {
    userInfo.value.id = infoStore.userInfo.id
    let result = await userUpdateService(userInfo.value)
    ElMessage.success(result.message ? result.message : '修改成功')
    //需要更新pinia中的用户信息
    infoStore.info.nickname=userInfo.value.nickname
    infoStore.info.email = userInfo.value.email
}

//用户信息回显
const userInfoGet = async() => {
    let result = await userInfoGetService()
    userInfo.value = result.data
}
userInfoGet()
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>基本资料</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <el-form :model="userInfo" :rules="rules" label-width="100px" size="large">
                    <el-form-item label="登录名称">
                        <el-input v-model="userInfo.username" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="用户昵称" prop="nickname">
                        <el-input v-model="userInfo.nickname"></el-input>
                    </el-form-item>
                    <el-form-item label="用户邮箱" prop="email">
                        <el-input v-model="userInfo.email"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submit">提交修改</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </el-card>
</template>