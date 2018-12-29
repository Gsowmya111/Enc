LOCAL_PATH := $(call my-dir)




include $(CLEAR_VARS)
LOCAL_MODULE := native-lib
LOCAL_SRC_FILES := $(TARGET_ARCH_ABI)/libnative-lib.so

LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/include
include $(PREBUILT_SHARED_LIBRARY)
