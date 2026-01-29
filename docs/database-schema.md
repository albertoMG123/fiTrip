# Base de datos propuesta (PostgreSQL)

Este esquema refleja el estado actual del modelo con:
- Usuarios normales y empresas.
- Deportes interior/exterior.
- Actividades/ofertas por empresa.
- Eventos para métricas (visitas, favoritos, compras).

## SQL (DDL)

```sql
-- Tipos auxiliares
CREATE TYPE user_role AS ENUM ('NORMAL', 'EMPRESA');
CREATE TYPE sport_category AS ENUM ('INTERIOR', 'EXTERIOR');
CREATE TYPE difficulty_level AS ENUM ('FACIL', 'MEDIA', 'DIFICIL');
CREATE TYPE event_type AS ENUM ('VISITA', 'FAVORITO', 'COMPRA');

-- Usuarios
CREATE TABLE usuarios (
  id BIGSERIAL PRIMARY KEY,
  firebase_uid TEXT UNIQUE NOT NULL,
  nombre TEXT NOT NULL,
  email TEXT UNIQUE NOT NULL,
  telefono TEXT,
  foto_url TEXT,
  rol user_role NOT NULL DEFAULT 'NORMAL',
  created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Empresas (perfil de empresa separado del usuario)
CREATE TABLE empresas (
  id BIGSERIAL PRIMARY KEY,
  usuario_id BIGINT NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
  nombre_empresa TEXT NOT NULL,
  descripcion TEXT,
  direccion TEXT,
  telefono TEXT,
  email_contacto TEXT,
  created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Catálogo de deportes
CREATE TABLE deportes (
  id BIGSERIAL PRIMARY KEY,
  nombre TEXT NOT NULL,
  categoria sport_category NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Actividades/ofertas (campos comunes)
CREATE TABLE actividades (
  id BIGSERIAL PRIMARY KEY,
  empresa_id BIGINT NOT NULL REFERENCES empresas(id) ON DELETE CASCADE,
  deporte_id BIGINT NOT NULL REFERENCES deportes(id),
  nombre TEXT NOT NULL,
  descripcion TEXT,
  precio NUMERIC(10,2),
  moneda TEXT DEFAULT 'EUR',
  imagen_principal_url TEXT,
  created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Detalles para actividades de interior
CREATE TABLE actividades_interior (
  actividad_id BIGINT PRIMARY KEY REFERENCES actividades(id) ON DELETE CASCADE,
  direccion TEXT NOT NULL,
  latitud DECIMAL(9,6),
  longitud DECIMAL(9,6),
  horario TEXT,
  servicios TEXT[],
  precio_mensual NUMERIC(10,2),
  precio_sesion NUMERIC(10,2),
  capacidad INTEGER
);

-- Detalles para actividades de exterior
CREATE TABLE actividades_exterior (
  actividad_id BIGINT PRIMARY KEY REFERENCES actividades(id) ON DELETE CASCADE,
  ubicacion TEXT NOT NULL,
  latitud DECIMAL(9,6) NOT NULL,
  longitud DECIMAL(9,6) NOT NULL,
  altitud INTEGER,
  distancia_km NUMERIC(6,2),
  duracion_min INTEGER,
  dificultad difficulty_level,
  punto_inicio_lat DECIMAL(9,6),
  punto_inicio_lng DECIMAL(9,6),
  punto_fin_lat DECIMAL(9,6),
  punto_fin_lng DECIMAL(9,6),
  temporada_recomendada TEXT,
  equipo_recomendado TEXT
);

-- Galería de imágenes para actividades
CREATE TABLE actividad_imagenes (
  id BIGSERIAL PRIMARY KEY,
  actividad_id BIGINT NOT NULL REFERENCES actividades(id) ON DELETE CASCADE,
  url TEXT NOT NULL,
  orden INTEGER DEFAULT 0
);

-- Eventos para métricas (visitas, favoritos, compras)
CREATE TABLE eventos_empresa (
  id BIGSERIAL PRIMARY KEY,
  empresa_id BIGINT NOT NULL REFERENCES empresas(id) ON DELETE CASCADE,
  actividad_id BIGINT REFERENCES actividades(id) ON DELETE SET NULL,
  usuario_id BIGINT REFERENCES usuarios(id) ON DELETE SET NULL,
  tipo event_type NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Índices recomendados
CREATE INDEX idx_eventos_empresa_tipo_fecha ON eventos_empresa (empresa_id, tipo, created_at);
CREATE INDEX idx_actividades_empresa ON actividades (empresa_id);
CREATE INDEX idx_actividades_deporte ON actividades (deporte_id);
```

## Métricas que se pueden consultar

- Visitas, favoritos y compras por empresa (tabla `eventos_empresa`).
- Producto más vendido (conteo de eventos `COMPRA` agrupado por `actividad_id`).
- Estadísticas por rango de fechas usando `created_at`.

## Notas

- Los campos de localización usan latitud/longitud para mapas.
- La separación `actividades_interior` / `actividades_exterior` mantiene el modelo limpio.
- Puedes ampliar el esquema con reservas, reviews y pagos cuando lo necesites.
