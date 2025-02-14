USE [master]
GO
/****** Object:  Database [PregnancyTracker]    Script Date: 2/18/2025 9:52:48 AM ******/
CREATE DATABASE [PregnancyTracker]
GO
ALTER DATABASE [PregnancyTracker] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PregnancyTracker] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PregnancyTracker] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PregnancyTracker] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PregnancyTracker] SET ARITHABORT OFF 
GO
ALTER DATABASE [PregnancyTracker] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PregnancyTracker] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PregnancyTracker] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PregnancyTracker] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PregnancyTracker] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PregnancyTracker] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PregnancyTracker] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PregnancyTracker] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PregnancyTracker] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PregnancyTracker] SET  DISABLE_BROKER 
GO
ALTER DATABASE [PregnancyTracker] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PregnancyTracker] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PregnancyTracker] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PregnancyTracker] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PregnancyTracker] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PregnancyTracker] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PregnancyTracker] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PregnancyTracker] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [PregnancyTracker] SET  MULTI_USER 
GO
ALTER DATABASE [PregnancyTracker] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PregnancyTracker] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PregnancyTracker] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PregnancyTracker] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [PregnancyTracker] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [PregnancyTracker] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [PregnancyTracker] SET QUERY_STORE = ON
GO
ALTER DATABASE [PregnancyTracker] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [PregnancyTracker]
GO
/****** Object:  Table [dbo].[Comment]    Script Date: 2/18/2025 9:52:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comment](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[PostID] [int] NOT NULL,
	[UserID] [int] NOT NULL,
	[Content] [nvarchar](max) NOT NULL,
	[Status] [nvarchar](50) NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CommunityPost]    Script Date: 2/18/2025 9:52:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CommunityPost](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[Title] [nvarchar](255) NOT NULL,
	[Content] [nvarchar](max) NOT NULL,
	[CommentCount] [int] NOT NULL,
	[AttachmentUrl] [varchar](255) NULL,
	[Status] [nvarchar](50) NULL,
	[CreatedDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FetalGrowthMeasurements]    Script Date: 2/18/2025 9:52:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FetalGrowthMeasurements](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[PregnancyProfileID] [int] NOT NULL,
	[WeekNumber] [int] NOT NULL,
	[MeasurementDate] [date] NOT NULL,
	[Weight] [decimal](5, 2) NOT NULL,
	[Height] [decimal](5, 2) NULL,
	[HeadCircumference] [decimal](5, 2) NULL,
	[BellyCircumference] [decimal](5, 2) NULL,
	[HeartRate] [int] NULL,
	[MovementCount] [int] NULL,
	[Notes] [nvarchar](max) NULL,
	[CreatedDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PregnancyProfile]    Script Date: 2/18/2025 9:52:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PregnancyProfile](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[NickName] [nvarchar](100) NOT NULL,
	[DueDate] [date] NOT NULL,
	[ConceptionDate] [date] NULL,
	[LastPeriodDate] [date] NULL,
	[PregnancyWeek] [int] NULL,
	[Notes] [nvarchar](max) NULL,
	[Status] [nvarchar](50) NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
	[UserID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RefreshToken]    Script Date: 2/18/2025 9:52:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RefreshToken](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Token] [nvarchar](255) NOT NULL,
	[ExpiredAt] [datetime] NOT NULL,
	[CreatedAt] [datetime] NOT NULL,
	[UserID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 2/18/2025 9:52:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[RoleName] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Schedule]    Script Date: 2/18/2025 9:52:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Schedule](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](20) NOT NULL,
	[Description] [ntext] NOT NULL,
	[EventDate] [date] NOT NULL,
	[Status] [nvarchar](50) NOT NULL,
	[IsCompleted] [bit] NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
	[UserID] [int] NOT NULL,
	[PregncacyID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SubscriptionPlan]    Script Date: 2/18/2025 9:52:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SubscriptionPlan](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NOT NULL,
	[Price] [decimal](10, 2) NOT NULL,
	[Duration] [int] NOT NULL,
	[Description] [nvarchar](max) NULL,
	[IsActive] [bit] NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
 CONSTRAINT [PK__Subscrip__3214EC272B5A4F93] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 2/18/2025 9:52:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Email] [varchar](255) NOT NULL,
	[Password] [varchar](255) NOT NULL,
	[FullName] [varchar](255) NOT NULL,
	[Address] [nvarchar](max) NULL,
	[DateOfBirth] [date] NULL,
	[AvatarUrl] [varchar](255) NULL,
	[RoleID] [int] NOT NULL,
	[EmailVerified] [bit] NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
	[Status] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserSubscription]    Script Date: 2/18/2025 9:52:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserSubscription](
	[UserSubscriptionID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[PlanID] [int] NOT NULL,
	[StartDate] [date] NOT NULL,
	[EndDate] [date] NOT NULL,
	[Amount] [decimal](10, 2) NOT NULL,
	[SubscriptionCode] [char](15) NOT NULL,
	[BankCode] [varchar](20) NULL,
	[TransactionNo] [varchar](15) NULL,
	[PaymentDate] [datetime] NULL,
	[Status] [nvarchar](50) NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
 CONSTRAINT [PK__UserSubs__D1FD775C1CC7D9F6] PRIMARY KEY CLUSTERED 
(
	[UserSubscriptionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[FetalGrowthMeasurements] ON 
GO
INSERT [dbo].[FetalGrowthMeasurements] ([ID], [PregnancyProfileID], [WeekNumber], [MeasurementDate], [Weight], [Height], [HeadCircumference], [BellyCircumference], [HeartRate], [MovementCount], [Notes], [CreatedDate]) VALUES (1, 1, 40, CAST(N'2025-02-10' AS Date), CAST(0.00 AS Decimal(5, 2)), CAST(0.00 AS Decimal(5, 2)), CAST(0.00 AS Decimal(5, 2)), CAST(0.00 AS Decimal(5, 2)), 0, 0, N'string', CAST(N'2025-02-10T14:38:53.330' AS DateTime))
GO
INSERT [dbo].[FetalGrowthMeasurements] ([ID], [PregnancyProfileID], [WeekNumber], [MeasurementDate], [Weight], [Height], [HeadCircumference], [BellyCircumference], [HeartRate], [MovementCount], [Notes], [CreatedDate]) VALUES (2, 1, 40, CAST(N'2025-02-12' AS Date), CAST(10.00 AS Decimal(5, 2)), CAST(10.00 AS Decimal(5, 2)), CAST(0.00 AS Decimal(5, 2)), CAST(0.00 AS Decimal(5, 2)), 0, 0, N'string', CAST(N'2025-02-12T04:03:44.830' AS DateTime))
GO
INSERT [dbo].[FetalGrowthMeasurements] ([ID], [PregnancyProfileID], [WeekNumber], [MeasurementDate], [Weight], [Height], [HeadCircumference], [BellyCircumference], [HeartRate], [MovementCount], [Notes], [CreatedDate]) VALUES (3, 2, 10, CAST(N'2025-02-12' AS Date), CAST(10.00 AS Decimal(5, 2)), CAST(10.00 AS Decimal(5, 2)), CAST(0.00 AS Decimal(5, 2)), CAST(0.00 AS Decimal(5, 2)), 0, 0, N'string', CAST(N'2025-02-12T04:03:51.753' AS DateTime))
GO
SET IDENTITY_INSERT [dbo].[FetalGrowthMeasurements] OFF
GO
SET IDENTITY_INSERT [dbo].[PregnancyProfile] ON 
GO
INSERT [dbo].[PregnancyProfile] ([ID], [NickName], [DueDate], [ConceptionDate], [LastPeriodDate], [PregnancyWeek], [Notes], [Status], [CreatedDate], [UserID]) VALUES (1, N'string', CAST(N'2025-02-10' AS Date), CAST(N'2025-02-10' AS Date), CAST(N'2025-02-10' AS Date), 0, N'string', N'string', CAST(N'2025-02-10T05:16:37.087' AS DateTime), 4)
GO
INSERT [dbo].[PregnancyProfile] ([ID], [NickName], [DueDate], [ConceptionDate], [LastPeriodDate], [PregnancyWeek], [Notes], [Status], [CreatedDate], [UserID]) VALUES (2, N'tesst2', CAST(N'2025-02-12' AS Date), CAST(N'2025-02-12' AS Date), CAST(N'2025-02-12' AS Date), 10, N'string', N'string', CAST(N'2025-02-12T04:03:27.440' AS DateTime), 4)
GO
SET IDENTITY_INSERT [dbo].[PregnancyProfile] OFF
GO
SET IDENTITY_INSERT [dbo].[RefreshToken] ON 
GO
INSERT [dbo].[RefreshToken] ([ID], [Token], [ExpiredAt], [CreatedAt], [UserID]) VALUES (10, N'e9b8c5a5-988b-45c6-9ad1-64ecb2fd4a82', CAST(N'2025-02-12T05:19:19.623' AS DateTime), CAST(N'2025-02-11T05:19:19.623' AS DateTime), 5)
GO
INSERT [dbo].[RefreshToken] ([ID], [Token], [ExpiredAt], [CreatedAt], [UserID]) VALUES (11, N'b0971922-043b-401e-aaf8-1ab4bed016fd', CAST(N'2025-02-13T04:02:33.040' AS DateTime), CAST(N'2025-02-12T04:02:33.040' AS DateTime), 4)
GO
INSERT [dbo].[RefreshToken] ([ID], [Token], [ExpiredAt], [CreatedAt], [UserID]) VALUES (12, N'98762796-2f0e-4c35-95f8-b9bd5adf52bc', CAST(N'2025-02-15T14:17:47.950' AS DateTime), CAST(N'2025-02-14T14:17:47.950' AS DateTime), 4)
GO
SET IDENTITY_INSERT [dbo].[RefreshToken] OFF
GO
SET IDENTITY_INSERT [dbo].[Role] ON 
GO
INSERT [dbo].[Role] ([ID], [RoleName]) VALUES (1, N'ROLE_ADMIN')
GO
INSERT [dbo].[Role] ([ID], [RoleName]) VALUES (2, N'ROLE_USER')
GO
INSERT [dbo].[Role] ([ID], [RoleName]) VALUES (3, N'ROLE_USER_PREMIUM')
GO
SET IDENTITY_INSERT [dbo].[Role] OFF
GO
SET IDENTITY_INSERT [dbo].[SubscriptionPlan] ON 
GO
INSERT [dbo].[SubscriptionPlan] ([ID], [Name], [Price], [Duration], [Description], [IsActive], [CreatedDate]) VALUES (1, N'Test month package', CAST(100000.00 AS Decimal(10, 2)), 30, N'Ke hoach dang ky trong 30 ngay', 1, CAST(N'2025-02-14T14:54:45.583' AS DateTime))
GO
INSERT [dbo].[SubscriptionPlan] ([ID], [Name], [Price], [Duration], [Description], [IsActive], [CreatedDate]) VALUES (2, N'Test plan2', CAST(120000.00 AS Decimal(10, 2)), 40, N'Ke hoach dang ky trong 40 ngay', 1, CAST(N'2025-02-14T14:55:10.810' AS DateTime))
GO
SET IDENTITY_INSERT [dbo].[SubscriptionPlan] OFF
GO
SET IDENTITY_INSERT [dbo].[User] ON 
GO
INSERT [dbo].[User] ([ID], [Email], [Password], [FullName], [Address], [DateOfBirth], [AvatarUrl], [RoleID], [EmailVerified], [CreatedDate], [Status]) VALUES (4, N'user@gmail.com', N'$2a$10$QAFKCbYgNbUCQeywH6lYYO/UHVFqLWu6tDBmDfXMg1rG.WihPCkX2', N'user full name', N'123 test, hcm', CAST(N'2000-10-10' AS Date), NULL, 2, 1, CAST(N'2025-01-08T14:09:53.727' AS DateTime), N'Active')
GO
INSERT [dbo].[User] ([ID], [Email], [Password], [FullName], [Address], [DateOfBirth], [AvatarUrl], [RoleID], [EmailVerified], [CreatedDate], [Status]) VALUES (5, N'admin@gmail.com', N'$2a$10$yRwVHdBfIR7G9ju147fwp.VgksOCIcMgn8Y0HB6j5ITHFTbo4qO22', N'admin', N'string', CAST(N'1996-02-11' AS Date), NULL, 1, 1, CAST(N'2025-02-11T05:18:18.880' AS DateTime), N'Active')
GO
SET IDENTITY_INSERT [dbo].[User] OFF
GO
SET IDENTITY_INSERT [dbo].[UserSubscription] ON 
GO
INSERT [dbo].[UserSubscription] ([UserSubscriptionID], [UserID], [PlanID], [StartDate], [EndDate], [Amount], [SubscriptionCode], [BankCode], [TransactionNo], [PaymentDate], [Status], [CreatedDate]) VALUES (1, 4, 1, CAST(N'2025-02-14' AS Date), CAST(N'2025-03-16' AS Date), CAST(100000.00 AS Decimal(10, 2)), N'180952315245593', NULL, NULL, NULL, N'PAYMENT_PENDING', CAST(N'2025-02-14T14:56:53.967' AS DateTime))
GO
INSERT [dbo].[UserSubscription] ([UserSubscriptionID], [UserID], [PlanID], [StartDate], [EndDate], [Amount], [SubscriptionCode], [BankCode], [TransactionNo], [PaymentDate], [Status], [CreatedDate]) VALUES (2, 4, 1, CAST(N'2025-02-14' AS Date), CAST(N'2025-03-16' AS Date), CAST(100000.00 AS Decimal(10, 2)), N'657461147789076', NULL, NULL, NULL, N'PAYMENT_PENDING', CAST(N'2025-02-14T14:58:48.603' AS DateTime))
GO
INSERT [dbo].[UserSubscription] ([UserSubscriptionID], [UserID], [PlanID], [StartDate], [EndDate], [Amount], [SubscriptionCode], [BankCode], [TransactionNo], [PaymentDate], [Status], [CreatedDate]) VALUES (3, 4, 1, CAST(N'2025-02-14' AS Date), CAST(N'2025-03-16' AS Date), CAST(100000.00 AS Decimal(10, 2)), N'639067408104137', NULL, NULL, NULL, N'PAYMENT_PENDING', CAST(N'2025-02-14T15:18:47.990' AS DateTime))
GO
INSERT [dbo].[UserSubscription] ([UserSubscriptionID], [UserID], [PlanID], [StartDate], [EndDate], [Amount], [SubscriptionCode], [BankCode], [TransactionNo], [PaymentDate], [Status], [CreatedDate]) VALUES (4, 4, 1, CAST(N'2025-02-14' AS Date), CAST(N'2025-03-16' AS Date), CAST(100000.00 AS Decimal(10, 2)), N'002655434586428', NULL, NULL, NULL, N'PAYMENT_PENDING', CAST(N'2025-02-14T15:23:32.313' AS DateTime))
GO
INSERT [dbo].[UserSubscription] ([UserSubscriptionID], [UserID], [PlanID], [StartDate], [EndDate], [Amount], [SubscriptionCode], [BankCode], [TransactionNo], [PaymentDate], [Status], [CreatedDate]) VALUES (5, 4, 1, CAST(N'2025-02-14' AS Date), CAST(N'2025-03-16' AS Date), CAST(100000.00 AS Decimal(10, 2)), N'960844504821841', NULL, NULL, NULL, N'PAYMENT_PENDING', CAST(N'2025-02-14T15:31:39.867' AS DateTime))
GO
INSERT [dbo].[UserSubscription] ([UserSubscriptionID], [UserID], [PlanID], [StartDate], [EndDate], [Amount], [SubscriptionCode], [BankCode], [TransactionNo], [PaymentDate], [Status], [CreatedDate]) VALUES (6, 4, 1, CAST(N'2025-02-14' AS Date), CAST(N'2025-03-16' AS Date), CAST(100000.00 AS Decimal(10, 2)), N'640765704305903', NULL, NULL, NULL, N'PAYMENT_PENDING', CAST(N'2025-02-14T15:33:54.727' AS DateTime))
GO
INSERT [dbo].[UserSubscription] ([UserSubscriptionID], [UserID], [PlanID], [StartDate], [EndDate], [Amount], [SubscriptionCode], [BankCode], [TransactionNo], [PaymentDate], [Status], [CreatedDate]) VALUES (7, 4, 1, CAST(N'2025-02-14' AS Date), CAST(N'2025-03-16' AS Date), CAST(100000.00 AS Decimal(10, 2)), N'088599694615260', N'NCB', N'14811642', CAST(N'2025-02-14T22:39:57.000' AS DateTime), N'PENDING', CAST(N'2025-02-14T15:39:24.050' AS DateTime))
GO
SET IDENTITY_INSERT [dbo].[UserSubscription] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__RefreshT__1EB4F8173E4C9548]    Script Date: 2/18/2025 9:52:49 AM ******/
ALTER TABLE [dbo].[RefreshToken] ADD UNIQUE NONCLUSTERED 
(
	[Token] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__User__A9D10534B77F00C7]    Script Date: 2/18/2025 9:52:49 AM ******/
ALTER TABLE [dbo].[User] ADD UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ_SubscriptionCode]    Script Date: 2/18/2025 9:52:49 AM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UQ_SubscriptionCode] ON [dbo].[UserSubscription]
(
	[SubscriptionCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[CommunityPost] ADD  DEFAULT (sysdatetime()) FOR [CreatedDate]
GO
ALTER TABLE [dbo].[FetalGrowthMeasurements] ADD  DEFAULT (sysdatetime()) FOR [CreatedDate]
GO
ALTER TABLE [dbo].[PregnancyProfile] ADD  DEFAULT ('active') FOR [Status]
GO
ALTER TABLE [dbo].[PregnancyProfile] ADD  DEFAULT (sysdatetime()) FOR [CreatedDate]
GO
ALTER TABLE [dbo].[SubscriptionPlan] ADD  CONSTRAINT [DF__Subscript__Creat__4AB81AF0]  DEFAULT (sysdatetime()) FOR [CreatedDate]
GO
ALTER TABLE [dbo].[User] ADD  DEFAULT ((0)) FOR [EmailVerified]
GO
ALTER TABLE [dbo].[User] ADD  DEFAULT (sysdatetime()) FOR [CreatedDate]
GO
ALTER TABLE [dbo].[UserSubscription] ADD  CONSTRAINT [DF__UserSubsc__Statu__534D60F1]  DEFAULT ('active') FOR [SubscriptionCode]
GO
ALTER TABLE [dbo].[UserSubscription] ADD  CONSTRAINT [DF__UserSubsc__Creat__52593CB8]  DEFAULT (sysdatetime()) FOR [CreatedDate]
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [FK_CommunityPost_Comment] FOREIGN KEY([PostID])
REFERENCES [dbo].[CommunityPost] ([ID])
GO
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [FK_CommunityPost_Comment]
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [FK_User_Comment] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([ID])
GO
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [FK_User_Comment]
GO
ALTER TABLE [dbo].[CommunityPost]  WITH CHECK ADD  CONSTRAINT [FK_User_CommunityPost] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([ID])
GO
ALTER TABLE [dbo].[CommunityPost] CHECK CONSTRAINT [FK_User_CommunityPost]
GO
ALTER TABLE [dbo].[FetalGrowthMeasurements]  WITH CHECK ADD  CONSTRAINT [FK_Pregnancy_FGM] FOREIGN KEY([PregnancyProfileID])
REFERENCES [dbo].[PregnancyProfile] ([ID])
GO
ALTER TABLE [dbo].[FetalGrowthMeasurements] CHECK CONSTRAINT [FK_Pregnancy_FGM]
GO
ALTER TABLE [dbo].[PregnancyProfile]  WITH CHECK ADD  CONSTRAINT [FL_User_Pregnancy] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([ID])
GO
ALTER TABLE [dbo].[PregnancyProfile] CHECK CONSTRAINT [FL_User_Pregnancy]
GO
ALTER TABLE [dbo].[RefreshToken]  WITH CHECK ADD  CONSTRAINT [FKRefreshTok703199] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([ID])
GO
ALTER TABLE [dbo].[RefreshToken] CHECK CONSTRAINT [FKRefreshTok703199]
GO
ALTER TABLE [dbo].[Schedule]  WITH CHECK ADD  CONSTRAINT [FK_Pregnancy_Schedule] FOREIGN KEY([PregncacyID])
REFERENCES [dbo].[PregnancyProfile] ([ID])
GO
ALTER TABLE [dbo].[Schedule] CHECK CONSTRAINT [FK_Pregnancy_Schedule]
GO
ALTER TABLE [dbo].[Schedule]  WITH CHECK ADD  CONSTRAINT [FK_User_Schedule] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([ID])
GO
ALTER TABLE [dbo].[Schedule] CHECK CONSTRAINT [FK_User_Schedule]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [FK_User_Role] FOREIGN KEY([RoleID])
REFERENCES [dbo].[Role] ([ID])
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [FK_User_Role]
GO
ALTER TABLE [dbo].[UserSubscription]  WITH CHECK ADD  CONSTRAINT [FK_Plan_UserSubscription] FOREIGN KEY([PlanID])
REFERENCES [dbo].[SubscriptionPlan] ([ID])
GO
ALTER TABLE [dbo].[UserSubscription] CHECK CONSTRAINT [FK_Plan_UserSubscription]
GO
ALTER TABLE [dbo].[UserSubscription]  WITH CHECK ADD  CONSTRAINT [FK_User_UserSubscription] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([ID])
GO
ALTER TABLE [dbo].[UserSubscription] CHECK CONSTRAINT [FK_User_UserSubscription]
GO
ALTER TABLE [dbo].[PregnancyProfile]  WITH CHECK ADD CHECK  (([PregnancyWeek]>=(0) AND [PregnancyWeek]<=(42)))
GO
USE [master]
GO
ALTER DATABASE [PregnancyTracker] SET  READ_WRITE 
GO
